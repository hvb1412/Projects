import { DataTypes } from "sequelize";

export const createCommentModel = (sequelize) => {
  const Comment = sequelize.define(
    "comments",
    {
      comment_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      post_id: {
        type: DataTypes.INTEGER,
      },
      user_id: {
        type: DataTypes.INTEGER,
      },
      parent_id: {
        type: DataTypes.INTEGER,
        allowNull: true,
        references: {
          model: "comments",
          key: "comment_id",
        },
      },
      content: {
        type: DataTypes.TEXT,
      },
    },
    {
      tableName: "comments",
      timestamps: true,
      createdAt: "created_at",
      updatedAt: "updated_at",
    }
  );

  return Comment;
};
