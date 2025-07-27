"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import Sidebar from "@/app/user_profile/Sidebar";
import AboutMe from "@/app/user_profile/AboutMe";
import UserPosts from "@/app/user_profile/UserPosts";

type Post = {
  post_id: number;
  title: string;
  content: string;
  status: string;
};

type UserData = {
  user_id: number;
  user_name: string;
  image: string | null;
  bio: string;
  address: string;
  phone_number: string;
  created_at: string;
  updated_at: string;
  user: {
    user_name: string;
    email: string;
  };
};

export default function UserProfilePage() {
  const [activeTab, setActiveTab] = useState<"about" | "posts">("about");
  const [postFilter, setPostFilter] = useState<"published" | "draft">("published");
  const [user, setUser] = useState<UserData | null>(null);
  const [posts, setPosts] = useState<Post[]>([]);
  const [bio, setBio] = useState("");
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");
  const router = useRouter();

  useEffect(() => {
    const fetchUserAndPosts = async () => {
      try {
        const storedUser = localStorage.getItem("user");
        if (!storedUser) return;

        const userData = JSON.parse(storedUser);
        const userId = userData.user_id || userData.id;
        if (!userId) return;

        const resUser = await fetch(`http://localhost:8000/user/profile/${userId}`);
        if (resUser.ok) {
          const data = await resUser.json();
          setUser(data);
          setBio(data.bio);
          setAddress(data.address);
          setPhone(data.phone_number);
        }

        const resPosts = await fetch(`http://localhost:8000/user/${userId}/allPosts`);
        if (resPosts.ok) {
          const data = await resPosts.json();
          setPosts(data);
        }
      } catch (err) {
        console.error("Lỗi khi lấy thông tin người dùng hoặc bài viết:", err);
      }
    };

    fetchUserAndPosts();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    router.push("/login");
  };

  const handleSave = async () => {
    if (!user) return;
    const res = await fetch(`http://localhost:8000/user/profile/${user.user_id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ bio, address, phone_number: phone }),
    });
    if (res.ok) {
      const updated = await res.json();
      setUser(updated);
    }
  };

  if (!user)
    return <p className="p-10 text-center text-gray-500">Đang tải thông tin người dùng...</p>;

  return (
    <div className="min-h-screen bg-gray-50 text-gray-800 flex">
      <Sidebar
        userName={user.user_name}
        activeTab={activeTab}
        setActiveTab={setActiveTab}
        handleLogout={handleLogout}
        router={router}
      />
      <main className="flex-1 p-6 md:p-10 ml-64">
        {activeTab === "about" && (
          <AboutMe
            bio={bio}
            address={address}
            phone={phone}
            email={user.user?.email || ""}
            onUpdate={({ bio, address, phone }) => {
              setBio(bio);
              setAddress(address);
              setPhone(phone);
              handleSave();
            }}
          />
        )}
        {activeTab === "posts" && (
          <UserPosts
            posts={posts}
            postFilter={postFilter}
            onFilterChange={setPostFilter}
          />
        )}
      </main>
    </div>
  );
}
