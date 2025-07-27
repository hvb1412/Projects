import { DataTypes } from "sequelize";

export const createPostModel = (sequelize) => {
  const Post = sequelize.define(
    "posts",
    {
      post_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      user_id: DataTypes.INTEGER,
      category_id: DataTypes.INTEGER,
      title: DataTypes.STRING(255),
      content: DataTypes.TEXT,
      status: DataTypes.STRING(50),
    },
    {
      tableName: "posts",
      timestamps: true,
      createdAt: "created_at",
      updatedAt: "updated_at",
    }
  );

  return Post;
};
