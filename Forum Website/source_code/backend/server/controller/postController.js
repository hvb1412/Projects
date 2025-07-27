import {
  PostModel,
  TagModel,
  PostTagModel,
  PostCategoryModel,
  CategoryModel,
  UserModel,
  CommentModel,
} from "../postgres/postgres.js";
import { Op } from "sequelize";

export const createPost = async (req, res) => {
  try {
    const { title, content, category_id, user_id, status, tags } = req.body;

    if (!title || !content || !category_id || !user_id || !status) {
      return res.status(400).json({ error: "Thiếu thông tin bắt buộc" });
    }

    // Tạo bài viết
    const newPost = await PostModel.create({
      title,
      content,
      user_id,
      category_id,
      status,
    });

    // Liên kết category (hỗ trợ 1 hoặc nhiều)
    const categoryIds = Array.isArray(category_id)
      ? category_id
      : [category_id];

    for (const catId of categoryIds) {
      await PostCategoryModel.create({
        post_id: newPost.post_id,
        category_id: catId,
      });
    }

    // Liên kết tags nếu có
    if (tags && Array.isArray(tags)) {
      for (const tagName of tags) {
        const [tag] = await TagModel.findOrCreate({ where: { name: tagName } });

        await PostTagModel.create({
          post_id: newPost.post_id,
          tag_id: tag.tag_id,
        });
      }
    }

    return res.status(201).json({
      message: "Bài viết đã được tạo thành công",
      post_id: newPost.post_id,
    });
  } catch (error) {
    console.error("Lỗi tạo bài viết:", error);
    res.status(500).json({ error: "Đã có lỗi khi tạo bài viết" });
  }
};

export const getPostById = async (req, res) => {
  try {
    const { post_id } = req.params;

    // Lấy post
    const post = await PostModel.findOne({
      where: { post_id: post_id },
      attributes: [
        "post_id",
        "user_id",
        "title",
        "content",
        "status",
        "created_at",
        "updated_at",
      ],
    });

    if (!post)
      return res.status(404).json({ error: "Không tìm thấy bài viết" });

    //Tìm user
    const author = await UserModel.findOne({
      where: { user_id: post.user_id },
      attributes: ["user_name"],
    });
    // Truy vấn tag và category
    const categoryRows = await CategoryModel.findAll({
      include: {
        model: PostModel,
        where: { post_id: post_id },
        through: { attributes: [] },
      },
    });

    const tagRows = await TagModel.findAll({
      include: {
        model: PostModel,
        where: { post_id: post_id },
        through: { attributes: [] },
      },
    });

    const categories = categoryRows.map((c) => c.name);
    const tags = tagRows.map((t) => t.name);

    return res.json({
      post_id: post.post_id,
      title: post.title,
      content: post.content,
      status: post.status,
      created_at: post.created_at,
      updated_at: post.updated_at,
      author_name: author.user_name || null,
      categories,
      tags,
    });
  } catch (error) {
    console.error("Lỗi khi lấy bài viết:", error);
    res.status(500).json({ error: "Lỗi server" });
  }
};

export const getAllPosts = async (req, res) => {
  try {
    // Lấy query page và limit từ request
    const page = parseInt(req.query.page) || 1;
    const limit = parseInt(req.query.limit) || 5;
    const offset = (page - 1) * limit;

    // Đếm tổng số bài viết
    const total = await PostModel.count({
      where: { status: "published" },
    });

    // Lấy danh sách bài viết theo phân trang
    const posts = await PostModel.findAll({
      where: { status: "published" },
      include: [
        {
          model: TagModel,
          through: { attributes: [] },
        },
      ],
      order: [["created_at", "DESC"]],
      limit,
      offset,
    });

    res.json({ data: posts, total });
  } catch (error) {
    console.error("Lỗi lấy danh sách bài viết:", error);
    res.status(500).json({ error: "Không thể lấy bài viết" });
  }
};

export const getFeaturedPosts = async (req, res) => {
  try {
    const posts = await PostModel.findAll({
      where: { status: "published" },
      limit: 3,
      order: [["created_at", "DESC"]],
      attributes: ["post_id", "title", "content"],
    });

    res.json(posts);
  } catch (error) {
    console.error("Lỗi lấy bài viết nổi bật:", error);
    res.status(500).json({ error: "Không thể lấy bài viết nổi bật" });
  }
};

export const getAllUserPost = async (req, res) => {
  const { user_id } = req.params;

  try {
    const posts = await PostModel.findAll({
      where: {
        user_id: user_id,
      },
      orderBy: {
        created_at: "desc",
      },
    });

    return res.status(200).json(posts);
  } catch (error) {
    console.error("Lỗi khi lấy bài viết:", error);
    return res.status(500).json({ message: "Lỗi server khi lấy bài viết" });
  }
};

export const searchPosts = async (req, res) => {
  const { query, page = 1, limit = 10 } = req.query;

  if (typeof query !== "string" || query.trim() === "") {
    return res.status(400).json({ error: "Thiếu từ khóa tìm kiếm" });
  }

  const pageNum = parseInt(page);
  const limitNum = parseInt(limit);
  const offset = (pageNum - 1) * limitNum;

  try {
    // 1. Tìm các bài viết khớp tiêu đề
    const titleMatches = await PostModel.findAll({
      where: {
        title: { [Op.iLike]: `%${query}%` },
      },
      order: [["created_at", "DESC"]],
    });

    // 2. Tìm các bài viết khớp nội dung, nhưng không nằm trong titleMatches
    const contentMatches = await PostModel.findAll({
      where: {
        content: { [Op.iLike]: `%${query}%` },
        post_id: {
          [Op.notIn]: titleMatches.map((post) => post.post_id),
        },
      },
      order: [["created_at", "DESC"]],
    });

    // 3. Kết hợp
    const allMatches = [...titleMatches, ...contentMatches];
    const total = allMatches.length;

    // 4. Cắt theo phân trang
    const paginatedResults = allMatches.slice(offset, offset + limitNum);

    return res.json({
      results: paginatedResults,
      total,
      page: pageNum,
      limit: limitNum,
    });
  } catch (error) {
    console.error("Lỗi tìm kiếm:", error);
    return res.status(500).json({ error: "Lỗi máy chủ" });
  }
};