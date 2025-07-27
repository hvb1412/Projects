import { DataTypes } from "sequelize";

export const createPostTagModel = (sequelize) => {
  const PostTag = sequelize.define(
    "post_tags",
    {
      post_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
      tag_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
    },
    {
      tableName: "post_tags",
      timestamps: false,
    }
  );

  return PostTag;
};
