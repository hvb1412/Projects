import { DataTypes } from "sequelize";

export const createTagModel = (sequelize) => {
  const Tag = sequelize.define(
    "tags",
    {
      tag_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      name: {
        type: DataTypes.STRING(100),
        allowNull: false,
        unique: true,
      },
    },
    {
      tableName: "tags",
      timestamps: false,
    }
  );

  return Tag;
};
