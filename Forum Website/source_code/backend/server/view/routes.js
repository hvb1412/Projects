import express from "express";
import { getAllUsers,
        addUser,
        deleteUser,
        loginUser,
        registerUser,
        getUserProfile,
        updateUserProfile
    } from "../controller/userController.js";
import { createPost, getAllPosts, getAllUserPost, getFeaturedPosts, getPostById, searchPosts } from "../controller/postController.js";
import { getAllTags } from "../controller/tagController.js";
import { createComment, getAllComments } from "../controller/commentController.js";
import { getAllCategoryPosts } from "../controller/categoryController.js";

const router=express.Router();

//LOGIN
router.post("/login", loginUser);
//REGISTER
router.post("/register", registerUser);

//CREATE POST
router.post("/posts", createPost);

//FEATURED POST
router.get("/posts/featured", getFeaturedPosts);

//GET POST BY POST_ID
router.get("/posts/:post_id", getPostById);

//TAGS
router.get("/tags", getAllTags);

//GET ALL CATEGORY'S POSTS
router.get("/category/:category_id", getAllCategoryPosts);
//GET ALL POSTS
router.get("/category", getAllPosts);

//SEARCH POSTS
router.get("/search", searchPosts);


//CRUD FOR COMMENTS
router.post("/posts/:post_id/comments", createComment);
router.get("/posts/:post_id/getAllComments", getAllComments);

//CRUD FOR USERS
router.post("/addUser", addUser);
router.get("/getAllUsers", getAllUsers);
//GET ALL USER'S POST
router.get("/user/:user_id/allPosts", getAllUserPost);
router.delete("/user/:userName", deleteUser);
//GET USER PROFILE
router.get("/user/profile/:user_id", getUserProfile);
router.put("/user/:user_id/update", updateUserProfile)



export default router;