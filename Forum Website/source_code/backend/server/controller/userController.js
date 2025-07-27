import { UserModel, UserProfileModel } from "../postgres/postgres.js";
import { Op } from "sequelize";

export const getAllUsers = async (req, res) => {
  try {
    const users = await UserModel.findAll();
    if (users.length == 0) {
      return res.status(200).json({ error: "users not found" });
    }
    return res.status(200).json(users);
  } catch (error) {
    console.log(error);
    return res.status(500).json({ error: "internal server error" });
  }
};

export const addUser = async (req, res) => {
  const { user_name, email, password, role } = req.body;
  try {
    const user = await UserModel.findOne({
      where: {
        [Op.or]: [{ email: email }, { user_name: user_name }],
      },
    });
    if (user == null) {
      await UserModel.create(req.body);
      return res.status(201).json({ message: "user added successfully" });
    }
    return res.status(200).json({ message: "user is already found" });
  } catch (e) {
    console.log(e);
    return res.status(500).json({ error: "internal server error" });
  }
};

export const updateUserProfile = async (req, res) => {
  const user_id = req.params.user_id;
  console.log("user_id nhận vào: ", req.params.user_id);
  const fieldsToUpdate = { ...req.body };

  if (!user_id) {
    return res.status(400).json({ error: "Thiếu user_id trong URL" });
  }

  try {
    // Xoá các trường không có giá trị
    Object.keys(fieldsToUpdate).forEach((key) => {
      if (
        fieldsToUpdate[key] === undefined ||
        fieldsToUpdate[key] === null ||
        fieldsToUpdate[key] === ""
      ) {
        delete fieldsToUpdate[key];
      }
    });

    if (Object.keys(fieldsToUpdate).length === 0) {
      return res.status(400).json({ error: "Không có thông tin nào để cập nhật" });
    }

    fieldsToUpdate.updated_at = new Date();

    // Nếu có user_name, thì cập nhật bảng users nữa
    if (fieldsToUpdate.user_name) {
      await UserModel.update(
        { user_name: fieldsToUpdate.user_name },
        { where: { user_id} } 
      );
    }

    // Cập nhật bảng user_profile
    const [updated] = await UserProfileModel.update(fieldsToUpdate, {
      where: { user_id },
    });

    if (updated === 0) {
      return res.status(404).json({ error: "Không tìm thấy người dùng" });
    }

    const updatedUser = await UserProfileModel.findOne({ where: { user_id } });

    return res.status(200).json({ message: "Cập nhật thành công", user: updatedUser });
  } catch (err) {
    console.error("Lỗi khi cập nhật hồ sơ:", err);
    return res.status(500).json({ error: "Lỗi máy chủ" });
  }
};

export const deleteUser = async (req, res) => {
    let userName = req.params.userName;
    try {
        const user = await UserModel.findOne({
            where: {user_name: userName}
        })
        if(user == null) {
            return res.status(404).json({message: "user not found"})
        }
        await user.destroy();
        return res.status(200).json({message: "deleted successfully"})

    } catch (e) {
        console.log(e)
        return res.status(500).json({"error":"internal server"})
    }
}

export const loginUser = async(req, res) => {
  const {email, password} = req.body;
  try {
    const user = await UserModel.findOne({
      where: {email: email,
        password: password
      }
    })
    if(user == null) {
      return res.status(401).json({message: "Invalid email or password"})
    }
    return res.status(200).json({message:"Login successfull", user})
  } catch (error) {
    console.log(error)
    return res.status(500).json({"error":"Internal server"})
  }
}

export const registerUser = async (req, res) => {
  const { user_name, email, password } = req.body;

  try {
    // Kiểm tra trùng
    const existingUser = await UserModel.findOne({
      where: {
        [Op.or]: [{ email }, { user_name }],
      },
    });

    if (existingUser) {
      return res.status(409).json({ message: "User already exists" });
    }

    // 1. Tạo user
    const newUser = await UserModel.create({
      user_name,
      email,
      password,
      role: "member",
    });

    // 2. Tạo user_profile đi kèm
    await UserProfileModel.create({
      user_id: newUser.user_id,
      user_name: newUser.user_name,
      bio: "",
      image: null,
      address: "",
      phone_number: "",
    });

    return res.status(201).json({ message: "User registered successfully" });
  } catch (error) {
    console.error("Register Error:", error);
    return res.status(500).json({ error: "Internal server error" });
  }
};

export const getUserProfile = async (req, res) => {
  const { user_id } = req.params;

  try {
    const userProfile = await UserProfileModel.findOne({
      where: { user_id },
      include: [
        {
          model: UserModel,
          attributes: ["user_name", "email"],
        },
      ],
    });

    if (!userProfile) {
      return res.status(404).json({ message: "User profile not found." });
    }

    return res.status(200).json(userProfile);
  } catch (error) {
    console.error("Error fetching user profile:", error);
    return res.status(500).json({ message: "Internal server error." });
  }
};
