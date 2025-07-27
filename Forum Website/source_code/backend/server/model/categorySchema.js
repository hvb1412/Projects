import { DataTypes } from "sequelize";

export const createCategoryModel = (sequelize) => {
  const Category = sequelize.define(
    "categories",
    {
      category_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      name: {
        type: DataTypes.STRING(100),
        allowNull: false,
        unique: true,
      },
      description: {
        type: DataTypes.TEXT
      },
    },
    {
      tableName: "categories",
      timestamps: false,
    }
  );

  return Category;
};
