import { PostModel, UserModel } from "../postgres/postgres.js";

export const getAllCategoryPosts = async (req, res) => {
  const { category_id } = req.params;
  const page = parseInt(req.query.page) || 1;
  const limit = parseInt(req.query.limit) || 5;
  const offset = (page - 1) * limit;

  try {
    const { rows, count } = await PostModel.findAndCountAll({
      where: { category_id, status: "published" },
      offset,
      limit,
      order: [["created_at", "DESC"]],
      include: [{ model: UserModel, attributes: ["user_name"] }],
    });

    const result = rows.map((post) => ({
      post_id: post.post_id,
      title: post.title,
      content: post.content,
      created_at: post.created_at,
      author_name: post.user.user_name,
      tags: post.tags || [],
    }));

    res.json({
      data: result,
      total: count,
      page,
      limit,
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: "Lỗi server" });
  }
};