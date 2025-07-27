import { DataTypes } from "sequelize";

export const createUserProfileModel = (sequelize) => {
  const UserProfile = sequelize.define(
    "user_profile",
    {
      user_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      user_name: {
        type: DataTypes.STRING(100),
      },
      image: {
        type: DataTypes.STRING(255),
      },
      bio: {
        type: DataTypes.TEXT,
      },
      address: {
        type: DataTypes.STRING(255),
      },
      phone_number: {
        type: DataTypes.STRING(50),
      },
    },
    {
      tableName: "user_profile",
      timestamps: true,
      createdAt: "created_at",
      updatedAt: "updated_at",
    }
  );

  return UserProfile;
};
