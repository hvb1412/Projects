"use client";

interface Post {
  post_id: number;
  title: string;
  content: string;
  status: string;
}

type PostFilter = "published" | "draft";

interface UserPostsProps {
  posts: Post[];
  postFilter: PostFilter;
  onFilterChange: (filter: PostFilter) => void;
}

export default function UserPosts({ posts, postFilter, onFilterChange }: UserPostsProps) {
  return (
    <div className="max-w-7xl mx-auto">
      <div className="flex gap-4 mb-6">
        <button
          onClick={() => onFilterChange("published")}
          className={`px-4 py-2 rounded-md text-sm font-medium border ${
            postFilter === "published"
              ? "bg-blue-600 text-white"
              : "border-gray-300 text-gray-700 hover:bg-gray-100"
          }`}
        >
          Bài đăng
        </button>
        <button
          onClick={() => onFilterChange("draft")}
          className={`px-4 py-2 rounded-md text-sm font-medium border ${
            postFilter === "draft"
              ? "bg-blue-600 text-white"
              : "border-gray-300 text-gray-700 hover:bg-gray-100"
          }`}
        >
          Nháp
        </button>
      </div>
      {posts.length > 0 ? (
        <ul className="space-y-6">
          {posts
            .filter((post) => post.status === postFilter)
            .map((post) => (
              <li
                key={post.post_id}
                className="bg-white border border-gray-200 p-4 rounded-lg shadow-sm hover:shadow-md transition"
              >
                <h2 className="text-xl font-semibold text-gray-800 mb-2">
                  {post.title}
                </h2>
                <p className="text-gray-600 text-sm whitespace-pre-line">
                  {post.content}
                </p>
              </li>
            ))}
        </ul>
      ) : (
        <p className="text-gray-500">Không có bài viết nào.</p>
      )}
    </div>
  );
}
