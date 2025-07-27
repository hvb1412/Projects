"use client";

import { useSearchParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import Header from "@/components/Header";
import { User } from "@/app/all-posts/types";

interface Post {
  post_id: number;
  title: string;
  content: string;
  created_at: string;
  author_name: string;
}

export default function SearchPage() {
  const searchParams = useSearchParams();
  const router = useRouter();
  const [user, setUser] = useState<User | null>(null);
  const query = searchParams.get("query") || "";
  const pageParam = parseInt(searchParams.get("page") || "1");
  const [results, setResults] = useState<Post[]>([]);
  const [loading, setLoading] = useState(false);
  const [total, setTotal] = useState(0);
  const limit = 5;
  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  useEffect(() => {
    if (typeof window !== "undefined") {
      const storedUser = localStorage.getItem("user");
      if (storedUser) {
        try {
          setUser(JSON.parse(storedUser));
        } catch {
          console.error("Lỗi parse user");
        }
      }
    }
    const fetchResults = async () => {
      if (!query.trim()) return;

      setLoading(true);
      try {
        const res = await fetch(
          `http://localhost:8000/search?query=${encodeURIComponent(
            query
          )}&page=${pageParam}&limit=${limit}`
        );
        if (!res.ok) throw new Error("Lỗi khi fetch");

        const data = await res.json();
        setResults(data.results);
        setTotal(data.total);
      } catch (err) {
        console.error("Lỗi khi tìm kiếm:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchResults();
  }, [query, pageParam]);

  const totalPages = Math.ceil(total / limit);

  const goToPage = (page: number) => {
    const newParams = new URLSearchParams(searchParams.toString());
    newParams.set("page", page.toString());
    router.push(`/search?${newParams.toString()}`);
  };

  return (
    <div className="min-h-screen bg-gray-100 px-6 pt-28 pb-16 max-w-4xl mx-auto">
      <Header user={user} handleLogout={handleLogout} />
      <h1 className="text-3xl font-bold mb-6 text-blue-700">
        Kết quả tìm kiếm cho: “{query}”
      </h1>

      {loading ? (
        <p className="text-gray-600">Đang tìm kiếm...</p>
      ) : results.length === 0 ? (
        <p className="text-gray-500">Không tìm thấy bài viết nào.</p>
      ) : (
        <>
          <ul className="space-y-6">
            {results.map((post) => (
              <li
                key={post.post_id}
                className="bg-white p-6 rounded-lg shadow hover:shadow-md transition"
              >
                <Link href={`/post/${post.post_id}`}>
                  <h2 className="text-xl font-semibold text-blue-600 hover:underline">
                    {post.title}
                  </h2>
                </Link>
                <p className="text-sm text-gray-600 mt-1">
                  {post.author_name} -{" "}
                  {new Date(post.created_at).toLocaleDateString("vi-VN")}
                </p>
                <p className="text-gray-700 mt-2 line-clamp-3">
                  {post.content}
                </p>
              </li>
            ))}
          </ul>

          {/* Phân trang */}
          <div className="flex justify-between items-center mt-8">
            <Button
              variant="outline"
              disabled={pageParam <= 1}
              onClick={() => goToPage(pageParam - 1)}
            >
              Trang trước
            </Button>

            <span className="text-gray-700">
              Trang {pageParam} / {totalPages}
            </span>

            <Button
              variant="outline"
              disabled={pageParam >= totalPages}
              onClick={() => goToPage(pageParam + 1)}
            >
              Trang sau
            </Button>
          </div>
        </>
      )}
    </div>
  );
}
