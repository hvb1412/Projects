import { TagModel } from "../postgres/postgres.js";

export const getAllTags = async (req, res) => {
  try {
    const tags = await TagModel.findAll();
    if (tags.length == 0) {
      return res.status(200).json({ error: "tags not found" });
    }
    return res.status(200).json(tags);
  } catch (error) {
    console.log(error);
    return res.status(500).json({ error: "internal server error" });
  }
};
