"use client";

import Link from "next/link";

interface Post {
  post_id: number;
  title: string;
  content: string;
  created_at: string;
  author_name: string;
  tags: string[]; 
}

interface Props {
  posts: Post[];
}

export default function CategoryPostList({ posts }: Props) {
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
            {post.tags.map((tag, index) => (
              <span
                key={`post-${post.post_id}-tag-${index}`}
                className="bg-green-100 text-green-800 px-3 py-1 rounded-full text-sm"
              >
                #{tag}
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
