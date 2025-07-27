"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { toast } from "sonner";
import { Tag, User } from "../types";

export function usePostForm() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [category, setCategory] = useState("");
  const [tags, setTags] = useState<string[]>([]);
  const [availableTags, setAvailableTags] = useState<Tag[]>([]);
  const [user, setUser] = useState<User | null>(null);
  const [isDraft, setIsDraft] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        const parsed = JSON.parse(storedUser);
        setUser(parsed);
      } catch {
        console.error("Lỗi parse user");
      }
    }

    fetch("http://localhost:8000/tags")
      .then((res) => res.json())
      .then(setAvailableTags)
      .catch(() => toast.error("Không thể tải danh sách tags"));
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!title || !content || !category) {
      toast.warning("Vui lòng điền đầy đủ thông tin!");
      return;
    }

    const postData = {
      title,
      content,
      category_id: parseInt(category),
      user_id: user?.user_id,
      status: isDraft ? "draft" : "published",
      tags,
    };

    try {
      const res = await fetch("http://localhost:8000/posts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(postData),
      });

      if (!res.ok) throw new Error("Gửi thất bại");

      const result = await res.json();
      toast.success(isDraft ? "💾 Đã lưu nháp!" : "🎉 Đã đăng bài!");
      router.push(result.post_id ? `/post/${result.post_id}` : "/");
    } catch {
      toast.error("Có lỗi xảy ra khi đăng bài");
    }
  };

  return {
    title, setTitle,
    content, setContent,
    category, setCategory,
    tags, setTags,
    isDraft, setIsDraft,
    availableTags,
    user,
    handleSubmit,
  };
}
