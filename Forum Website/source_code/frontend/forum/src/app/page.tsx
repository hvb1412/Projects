"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import Header from "@/components/Header";
import CategoryGrid from "./CategoryGrid";
import FeaturedPosts from "./FeaturedPosts";
import CallToAction from "./CallToAction";

interface User {
  user_id: number;
  user_name: string;
  email: string;
  role: string;
}

export default function ForumHomePage() {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  return (
    <main className="min-h-screen bg-gray-100 text-gray-800 pt-17">
      <Header
        user={user}
        handleLogout={() => {
          localStorage.removeItem("user");
          window.location.href = "/login";
        }}
      />
      <section className="text-center py-16 px-4 bg-gradient-to-br from-blue-100 to-white">
        <h2 className="text-3xl font-semibold mb-2">
          Chào mừng đến với Diễn Đàn!
        </h2>
        <p className="text-lg text-gray-600 mb-6">
          Nơi bạn có thể thảo luận, đặt câu hỏi và chia sẻ kiến thức cùng cộng
          đồng.
        </p>
        {user ? (
          <Link href="/post">
            <Button className="px-6 text-base">Vào trang đăng bài</Button>
          </Link>
        ) : (
          <Link href="/register">
            <Button className="px-6 text-base">Tham gia ngay</Button>
          </Link>
        )}
      </section>

      <CategoryGrid />
      <FeaturedPosts />
      <CallToAction user={user} />

      <footer className="text-center py-6 text-sm text-gray-500 border-t mt-12">
        &copy; 2025 DevShare Lite. All rights reserved.
      </footer>
    </main>
  );
}
