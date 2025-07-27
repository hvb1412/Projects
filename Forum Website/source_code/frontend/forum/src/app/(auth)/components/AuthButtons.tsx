"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { Button } from "@/components/ui/button";

export default function AuthButtons() {
  const router = useRouter();
  const [user, setUser] = useState<{ user_name: string } | null>(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        const parsedUser = JSON.parse(storedUser);
        setUser(parsedUser);
      } catch (err) {
        console.error("Lỗi khi parse user từ localStorage:", err);
      }
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
    window.location.reload();
  };

  if (user) {
    return (
      <div className="flex items-center gap-4">
        <span className="text-base font-semibold text-gray-800">
          Xin chào, <b>{user.user_name}</b>
        </span>
        <Button variant="outline" className="text-sm" onClick={handleLogout}>
          Logout
        </Button>
      </div>
    );
  }

  return (
    <>
      <Button variant="ghost" onClick={() => router.push("/login")}>
        Login
      </Button>
      <Button onClick={() => router.push("/register")}>
        Sign Up
      </Button>
    </>
  );
}
