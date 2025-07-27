"use client";

import Link from "next/link";
import { Post } from "./types";

interface AllPostsListProps {
  posts: Post[];
}

const categories = [
  { id: 1, name: "Lập trình" },
  { id: 2, name: "Thiết kế" },
  { id: 3, name: "Hỏi đáp" },
  { id: 4, name: "Công nghệ" },
  { id: 5, name: "Tin tức" },
  { id: 6, name: "Khác" },
];

export default function AllPostsList({ posts }: AllPostsListProps) {
  const getCategoryName = (id: number) => {
    const category = categories.find((cat) => cat.id === id);
    return category ? category.name : "Không rõ";
  };

  return (
    <ul className="space-y-6">
      {posts.map((post) => (
        <li
          key={post.post_id}
          className="bg-white p-6 rounded-xl shadow hover:shadow-lg transition"
        >
          <Link
            href={`/post/${post.post_id}`}
            className="text-xl font-semibold text-blue-700 hover:underline"
          >
            {post.title}
          </Link>
          <p className="text-sm text-gray-600 mt-1">
            Tác giả: {post.author_name} | Ngày{" "}
            {new Date(post.created_at).toLocaleDateString("vi-VN")}
          </p>
          <div className="flex flex-wrap gap-2 mt-2">
            {/* Link đến trang chuyên mục */}
            <Link href={`/category/${post.category_id}`}>
              <span className="cursor-pointer bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm hover:bg-blue-200 transition">
                #{getCategoryName(post.category_id)}
              </span>
            </Link>
            {post.tags.map((tag, index) => (
              <span
                key={`${post.post_id}-tag-${index}`}
                className="bg-green-100 text-green-800 px-3 py-1 rounded-full text-sm"
              >
                #{tag.name}
              </span>
            ))}
          </div>

          <p className="text-gray-700 mt-3 line-clamp-3 whitespace-pre-line">
            {post.content}
          </p>
        </li>
      ))}
    </ul>
  );
}
