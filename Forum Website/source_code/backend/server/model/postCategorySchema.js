import { DataTypes } from "sequelize";

export const createPostCategoryModel = (sequelize) => {
  const PostCategory = sequelize.define(
    "post_categories",
    {
      post_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
      category_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
    },
    {
      tableName: "post_categories",
      timestamps: false,
    }
  );

  return PostCategory;
};
