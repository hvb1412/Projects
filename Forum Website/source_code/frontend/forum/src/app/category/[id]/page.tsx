"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import CategoryPostList from "./CategoryPostList";
import { useCategoryPosts } from "./useCategoryPosts";
import Header from "@/components/Header";

const categories = [
  { id: 1, name: "Lập trình" },
  { id: 2, name: "Thiết kế" },
  { id: 3, name: "Hỏi đáp" },
  { id: 4, name: "Công nghệ" },
  { id: 5, name: "Tin tức" },
  { id: 6, name: "Khác" },
];

export default function CategoryPage() {
  const { id } = useParams();
  const router = useRouter();
  const categoryName = categories.find((cat) => cat.id === Number(id))?.name || "Không rõ";

  const [user, setUser] = useState<{ user_id: number; user_name: string } | null>(null);
  const { posts, loading, page, setPage, totalPages } = useCategoryPosts(id as string);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        setUser(JSON.parse(storedUser));
      } catch {
        console.error("Lỗi khi parse user");
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
      <Header user={user} handleLogout={handleLogout}/>

      <section className="max-w-7xl mx-auto p-6">
        <h2 className="text-3xl font-bold text-blue-600 mb-6">
          📚 Bài viết theo chuyên mục: <span className="italic text-blue-600">{categoryName}</span>
        </h2>
        {loading ? (
          <p>Đang tải...</p>
        ) : posts.length === 0 ? (
          <p className="text-gray-500">Không có bài viết nào trong chuyên mục này.</p>
        ) : (
          <>
            <CategoryPostList posts={posts} />
            <div className="flex justify-center mt-8 gap-4">
              <Button variant="outline" disabled={page === 1} onClick={() => setPage(page - 1)}>← Trước</Button>
              <span className="text-sm mt-2">Trang {page} / {totalPages || 1}</span>
              <Button variant="outline" disabled={page >= totalPages} onClick={() => setPage(page + 1)}>Tiếp →</Button>
            </div>
          </>
        )}
      </section>
    </main>
  );
}
