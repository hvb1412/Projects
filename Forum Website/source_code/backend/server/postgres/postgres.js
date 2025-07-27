import { Sequelize, DataTypes } from "sequelize";
import { createUserModel } from "../model/userSchema.js";
import { createPostModel } from "../model/postSchema.js";
import { createTagModel } from "../model/tagSchema.js";
import { createPostTagModel } from "../model/postTagSchema.js";
import { createCategoryModel } from "../model/categorySchema.js";
import { createPostCategoryModel } from "../model/postCategorySchema.js";
import { createUserProfileModel } from "../model/userProfileSchema.js";
import { createCommentModel } from "../model/commentSchema.js";

const sequelize = new Sequelize("NextForum", "postgres", "123456789binh", {
  host: "localhost",
  dialect: "postgres",
  timezone: "+07:00",
});

const UserModel = createUserModel(sequelize);
const PostModel = createPostModel(sequelize);
const TagModel = createTagModel(sequelize);
const PostTagModel = createPostTagModel(sequelize);
const CategoryModel = createCategoryModel(sequelize);
const PostCategoryModel = createPostCategoryModel(sequelize);
const UserProfileModel = createUserProfileModel(sequelize);
const CommentModel = createCommentModel(sequelize);

// Thiết lập mối quan hệ
UserModel.hasMany(PostModel, { foreignKey: "user_id" });
PostModel.belongsTo(UserModel, { foreignKey: "user_id" });

// Post - Tag (many-to-many)
PostModel.belongsToMany(TagModel, {
  through: PostTagModel,
  foreignKey: "post_id",
  otherKey: "tag_id",
});
TagModel.belongsToMany(PostModel, {
  through: PostTagModel,
  foreignKey: "tag_id",
  otherKey: "post_id",
});

// Post - Category (many-to-many)
PostModel.belongsToMany(CategoryModel, {
  through: PostCategoryModel,
  foreignKey: "post_id",
  otherKey: "category_id",
});
CategoryModel.belongsToMany(PostModel, {
  through: PostCategoryModel,
  foreignKey: "category_id",
  otherKey: "post_id",
});

// User - Comment (one-to-many)
UserModel.hasMany(CommentModel, { foreignKey: "user_id" });
CommentModel.belongsTo(UserModel, { foreignKey: "user_id" });

// Post - Comment (one-to-many)
PostModel.hasMany(CommentModel, { foreignKey: "post_id", onDelete: "CASCADE"});
CommentModel.belongsTo(PostModel, { foreignKey: "post_id" });

// User - UserProfile (one-to-one)
UserModel.hasOne(UserProfileModel, { foreignKey: "user_id" });
UserProfileModel.belongsTo(UserModel, { foreignKey: "user_id" });

CommentModel.hasMany(CommentModel, {
  foreignKey: "parent_id",
  as: "replies"
});

CommentModel.belongsTo(CommentModel, {
  foreignKey: "parent_id",
  as: "parent"
});



// Đồng bộ cơ sở dữ liệu
const connection = async () => {
  try {
    await sequelize.authenticate();
    console.log("Connected to PostgreSQL successfully.");
  } catch (error) {
    console.error("Unable to connect to the database:", error);
  }
};

export {
  connection,
  UserModel,
  PostModel,
  TagModel,
  PostTagModel,
  CategoryModel,
  PostCategoryModel,
  UserProfileModel,
  CommentModel,
};
