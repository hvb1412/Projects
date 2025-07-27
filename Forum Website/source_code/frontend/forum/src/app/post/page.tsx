"use client";

import { useEffect, useState } from "react";
import Header from "../../components/Header";
import PostForm from "./components/PostForm";
import { User } from "./types";

export default function CreatePostPage() {
  const [user, setUser] = useState<User | null>(null);

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
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <main className="min-h-screen bg-gray-50 pt-17">
      <Header user={user} handleLogout={handleLogout} />
      <section className="max-w-3xl mx-auto mt-10">
        <h2 className="text-3xl font-bold text-center mb-2">
          ✍️ Tạo bài viết mới
        </h2>
        <p className="text-center text-gray-500 mb-6">
          Chia sẻ kiến thức, kinh nghiệm hoặc đặt câu hỏi
        </p>
        <PostForm />
      </section>
    </main>
  );
}
