import { CommentModel, UserModel } from "../postgres/postgres.js";


export const createComment = async (req, res) => {
  const { post_id } = req.params;
  const { content, user_id, parent_id } = req.body;

  if (!user_id) return res.status(401).json({ message: "Unauthorized" });

  try {
    const user = await UserModel.findOne({
      where: { user_id }
    });

    if (!user) return res.status(404).json({ message: "User not found" });

    const comment = await CommentModel.create({
      post_id,
      user_id,
      content,
      parent_id: parent_id || null, // dùng đúng tên
    });

    return res.status(201).json({
      ...comment.toJSON(),
      user_name: user.user_name
    });
  } catch (err) {
    res.status(500).json({ message: "Lỗi tạo bình luận", error: err });
  }
};


export const getAllComments = async (req, res) => {
  const { post_id } = req.params;

  try {
    const comments = await CommentModel.findAll({
      where: { post_id },
      include: [
        {
          model: UserModel,
          attributes: ["user_name"], // chỉ lấy user_name
        },
      ],
      order: [["created_at", "DESC"]], // tuỳ chọn: sắp xếp mới nhất trước
    });

    if (!comments || comments.length === 0) {
      return res.status(404).json({ error: "No comments found" });
    }

    // Format dữ liệu gửi về
    const result = comments.map((comment) => ({
      comment_id: comment.comment_id,
      content: comment.content,
      created_at: comment.created_at,
      user_name: comment.user?.user_name || "Ẩn danh",
    }));

    return res.status(200).json(result);
  } catch (error) {
    console.error("Error fetching comments:", error);
    return res.status(500).json({ error: "Internal server error" });
  }
};


