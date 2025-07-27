"use client";

import Header from "@/components/Header";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

interface User {
  user_id: number;
  user_name: string;
  email: string;
  role: string;
  created_at: string;
}

export default function MembersPage() {
  const router = useRouter();
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [user, setUser] = useState<{
    user_id: number;
    user_name: string;
  } | null>(null);
  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
    router.push("/login");
  };

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        setUser(JSON.parse(storedUser));
      } catch {
        console.error("Lỗi khi parse user");
      }
    }
    fetch("http://localhost:8000/getAllUsers")
      .then((res) => res.json())
      .then((data) => {
        if (Array.isArray(data)) setUsers(data);
        else setUsers([]);
      })
      .catch(() => setUsers([]))
      .finally(() => setLoading(false));
  }, []);
  return (
    <main className="min-h-screen bg-gray-50 p-8 pt-20">
      <Header user={user} handleLogout={handleLogout} />
      <h1 className="text-2xl font-bold mb-6">Danh sách thành viên</h1>

      {loading ? (
        <p>Loading...</p>
      ) : users.length === 0 ? (
        <p className="text-gray-600">Không có thành viên nào.</p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {users.map((user) => (
            <div
              key={user.user_id}
              className="bg-white rounded-xl shadow p-4 border border-gray-200"
            >
              <h2 className="text-lg font-semibold mb-1">{user.user_name}</h2>
              <p className="text-sm text-gray-600">Email: {user.email}</p>
              <p className="text-sm text-gray-600">Vai trò: {user.role}</p>
              <p className="text-xs text-gray-400 mt-1">
                Ngày tạo:{" "}
                {new Date(user.created_at).toLocaleDateString("vi-VN")}
                <br />
              </p>
            </div>
          ))}
        </div>
      )}
    </main>
  );
}
