"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useAllPosts } from "./useAllPosts";
import AllPostsList from "./AllPostsList";
import Header from "@/components/Header";
import { Button } from "@/components/ui/button";

interface User {
  user_id: number;
  user_name: string;
}

export default function AllPostsPage() {
  const router = useRouter();
  const { posts, loading, page, setPage, totalPages } = useAllPosts();

  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        setUser(JSON.parse(storedUser));
      } catch (error) {
        console.error("Lỗi khi parse user từ localStorage:", error);
      }
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
    router.push("/login");
  };

  return (
    <main className="min-h-screen bg-gray-50 pb-40 pt-17">
      <Header user={user} handleLogout={handleLogout} />

      <section className="max-w-7xl mx-auto p-6">
        <h2 className="text-3xl font-bold text-blue-600 mb-6">
          📰 Tất cả bài viết
        </h2>

        {loading ? (
          <p>Đang tải...</p>
        ) : posts.length === 0 ? (
          <p className="text-gray-500">Không có bài viết nào.</p>
        ) : (
          <>
            <AllPostsList posts={posts} />
            <div className="flex justify-center mt-8 gap-4">
              <Button
                variant="outline"
                disabled={page === 1}
                onClick={() => setPage(page - 1)}
              >
                ← Trước
              </Button>
              <span className="text-sm mt-2">
                Trang {page} / {totalPages || 1}
              </span>
              <Button
                variant="outline"
                disabled={page >= totalPages}
                onClick={() => setPage(page + 1)}
              >
                Tiếp →
              </Button>
            </div>
          </>
        )}
      </section>
    </main>
  );
}
