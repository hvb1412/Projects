import { DataTypes } from "sequelize";

export const createUserModel = (sequelize) => {
  const User = sequelize.define(
    "users",
    {
      user_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      user_name: {
        type: DataTypes.STRING(100),
      },
      email: {
        type: DataTypes.STRING(255),
      },
      password: {
        type: DataTypes.TEXT,
      },
      role: {
        type: DataTypes.STRING(50),
      },
    },
    {
      tableName: "users",
      timestamps: true,
      createdAt: "created_at",
      updatedAt: "updated_at",
    }
  );

  return User;
};
