"use client";

import { useEffect, useState } from "react";
import Link from "next/link";

interface Post {
  post_id: number;
  title: string;
  content: string;
}

export default function FeaturedPosts() {
  const [posts, setPosts] = useState<Post[]>([]);

  useEffect(() => {
    const fetchFeaturedPosts = async () => {
      try {
        const res = await fetch("http://localhost:8000/posts/featured");
        const data = await res.json();
        setPosts(data);
      } catch (error) {
        console.error("Lỗi khi tải bài viết nổi bật:", error);
      }
    };

    fetchFeaturedPosts();
  }, []);

  return (
    <section className="bg-white py-12 px-6 border-t">
      <div className="max-w-5xl mx-auto">
        <h3 className="text-2xl font-semibold mb-6">Bài viết nổi bật</h3>
        {posts.length === 0 ? (
          <p className="text-gray-500">Chưa có bài viết nổi bật.</p>
        ) : (
          <ul className="space-y-4">
            {posts.map((post) => (
              <Link href={`/post/${post.post_id}`} key={post.post_id}>
                <li className="p-4 border rounded hover:bg-gray-50 cursor-pointer">
                  <h5 className="font-medium text-lg">{post.title}</h5>
                  <p className="text-sm text-gray-500">
                    {post.content.length > 120
                      ? post.content.slice(0, 120) + "..."
                      : post.content}
                  </p>
                </li>
              </Link>
            ))}
          </ul>
        )}
      </div>
    </section>
  );
}
