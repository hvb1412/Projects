# 🚀 Features of the DevShare Lite Project

## Login Feature
Users can log in if they already have an account ![alt text](./screenshots/login.png)

## Sign-Up Feature
If users don’t have an account, they can register ![alt text](./screenshots/signup.png)

## Logout Feature
Users can log out by clicking the "Logout" button ![alt text](./screenshots/logout.png)
**Result**: ![](./screenshots/logout_result.png)

## Return to Homepage
Return to the homepage by clicking the "Home" button ![alt text](./screenshots/home_page.png)

## Navigate to the Forum with All Posts (AllPosts)
Clicking the "Forum" button takes users to the page that shows all posts ![alt text](./screenshots/all-posts.png)
**Note**: You can navigate to a post's category page by clicking the category label below the title, e.g., "#Other" ![alt text](./screenshots/route_category.png)

## Create a Post
Access the post creation page by clicking "Create Post", "Go to Post Page", or "Start Posting" ![alt text](./screenshots/post.png)

## Redirect to Published or Draft Post Page
Automatically redirect to the corresponding post page after clicking "Publish" or "Save Draft", along with a toast notification ![alt text](./screenshots/post_published_drafted.png)

## Commenting
Comment on a post ![alt text](./screenshots/comment.png)
**Result**: ![alt text](./screenshots/comment_result.png)

## Reply to Comment
Click the "Reply" button to respond to a comment ![alt text](./screenshots/comment_response.png)
**Result**: ![alt text](./screenshots/comment_response_result.png)

## Navigate to Specific Category Page
Choose a category on the homepage, or click the "Category" button and select the category you want to view ![alt text](./screenshots/category.png)
**Result**: ![alt text](./screenshots/category_posts.png)

## Forum Members
Display the list of forum members by clicking the "Members" button ![alt text](./screenshots/members.png)

## Search Posts
Search by entering content into the search bar and pressing Enter ![alt text](./screenshots/search.png)
**Result**: Result ![alt text](./screenshots/search_result.png)

## View User Profile
Click the "Profile" button to view the user profile ![alt text](./screenshots/profile.png)
**Result**: ![alt text](./screenshots/profile_view.png)

## View User's Published and Draft Posts
Click the "Posts" button ![alt text](./screenshots/user_posts.png)

## Edit User Information
Click the "Edit Info" button in the About section ![alt text](./screenshots/profile_update.png)
*In the image*: The user is named Đỗ Hoàng Nam and the About me section says: No description
Edit user information
Update info: ![alt text](./screenshots/profile_update_info.png)
**Result**:Info updated ![alt text](./screenshots/profile_update_info_result.png)

---

## 🚀 Advanced Features Implemented
* Page displaying **the list of forum members**.
* Ability to **edit user profile information (name, address, bio)**.
* Clear distinction between **published posts** and **drafts**.
* Automatic **redirection** after publishing or saving a post, with **toast notifications**.
* **Pagination system** for posts.
* Ability to **access posts by category**.
* **Featured posts auto-update** on the homepage.

## 🛠️ Issues & Solutions
**Issue**: Incorrect redirection after publishing/saving a post
*Solution*: Used useNavigate() from React Router for proper navigation after each action, and updated the UI state to reflect changes.

**Issue**: Comments not appearing immediately after submission
*Solution*: Added logic to re-fetch comment data after successful submission. Used useEffect or state updates to trigger a re-render of the comment list.

**Issue**: Profile information not updating after editing
*Solution*: After a successful API update, refreshed the local state or re-fetched user data to display the latest info in the UI.

**Issue**: Search results were inaccurate (due to Markdown formatting or keyword mismatch)
*Solution*: Improved the search algorithm by stripping Markdown formatting, converting content and keywords to lowercase, and matching both title and body content.

## ⚠️ Current Limitations
* **No like/vote system** for posts and comments yet.
* **No notification system** for replies or new comments.
* **Single-level comment** replies only (no nested threading).
* **No admin** panel for post moderation.
* Users cannot **embed images** or **use advanced formatting** in posts.

## 🌱 Future Development Plans
* Add **a like/dislike system** for posts and comments.
* **Integrate Markdown preview**, with support for **image embedding** and **rich formatting**.
* Build **a notification system** for user interactions (e.g., someone comments on your post).
* Add **an admin dashboard** for approving posts and managing user violations.
* Improve **role-based access control**: clearly separate admin, moderator, and regular user.
* **Enhance mobile UI** and improve accessibility.

---
