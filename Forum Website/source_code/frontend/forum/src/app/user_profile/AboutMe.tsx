"use client";

import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import { useState, useEffect } from "react";
import { Pencil } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { User } from "@/app/all-posts/types";
import { Textarea } from "@/components/ui/textarea";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";

interface AboutMeProps {
  user_name?: string;
  bio?: string;
  address?: string;
  phone_number?: string;
  image?: string;
  email?: string;
  onUpdateSuccess?: (data: Partial<UserProfile>) => void;
}

interface UserProfile {
  user_id: number;
  user_name: string;
  bio: string;
  address: string;
  phone_number: string;
  image?: string;
  email: string;
}

export default function AboutMe({
  user_name = "",
  bio = "",
  address = "",
  phone_number = "",
  email = "",
  image = "",
  onUpdateSuccess,
}: AboutMeProps) {
  const [newName, setNewName] = useState(user_name);
  const [newBio, setNewBio] = useState(bio);
  const [newAddress, setNewAddress] = useState(address);
  const [newPhone, setNewPhone] = useState(phone_number);
  const [newImage, setNewImage] = useState(image);
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useState<User | null>(null);

  // Lấy user từ localStorage
  useEffect(() => {
    if (typeof window !== "undefined") {
      const storedUser = localStorage.getItem("user");
      if (storedUser) {
        try {
          const parsedUser = JSON.parse(storedUser);
          setUser(parsedUser);
        } catch (err) {
          console.error("Lỗi parse user:", err);
        }
      }
    }
  }, []);

  // Cập nhật form nếu props thay đổi
  useEffect(() => {
    setNewName(user_name ?? "");
    setNewBio(bio ?? "");
    setNewAddress(address ?? "");
    setNewPhone(phone_number ?? "");
    setNewImage(image ?? "");
  }, [user_name, bio, address, phone_number, image]);

  const handleSave = async () => {
    if (!user?.user_id) {
      alert("Không tìm thấy ID người dùng.");
      return;
    }

    setLoading(true);
    try {
      const res = await fetch(`http://localhost:8000/user/${user.user_id}/update`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          user_name: newName,
          bio: newBio,
          address: newAddress,
          phone_number: newPhone,
          image: newImage,
        }),
      });

      const data = await res.json();

      if (res.ok) {
        window.location.reload();
        const updated = { ...user, user_name: data.user.user_name };
        setUser(updated);
        localStorage.setItem("user", JSON.stringify(updated));

        if (onUpdateSuccess) {
          onUpdateSuccess(data.user);
        }
      } else {
        alert("Cập nhật thất bại: " + (data.error || "Lỗi không rõ"));
      }
    } catch (err) {
      console.error("Lỗi cập nhật:", err);
      alert("Có lỗi xảy ra khi cập nhật.");
    } finally {
      setLoading(false);
    }
  };

  const isUnchanged =
    newName === user_name &&
    newBio === bio &&
    newAddress === address &&
    newPhone === phone_number &&
    newImage === image;

  // Nếu user chưa load xong, không render
  if (!user) return <p className="text-gray-500">Đang tải thông tin...</p>;

  return (
    <div className="max-w-3xl mx-auto">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold text-blue-600">About Me</h1>
        <Dialog>
          <DialogTrigger asChild>
            <Button variant="outline" size="sm">
              <Pencil className="w-4 h-4 mr-1" /> Sửa thông tin
            </Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Chỉnh sửa thông tin</DialogTitle>
            </DialogHeader>
            <div className="space-y-4">
              <label className="block text-sm font-medium">Tên người dùng</label>
              <Input value={newName} onChange={(e) => setNewName(e.target.value)} />

              <label className="block text-sm font-medium">Mô tả bản thân</label>
              <Textarea value={newBio} onChange={(e) => setNewBio(e.target.value)} rows={4} />

              <label className="block text-sm font-medium">Địa chỉ</label>
              <Input value={newAddress} onChange={(e) => setNewAddress(e.target.value)} />

              <label className="block text-sm font-medium">Số điện thoại</label>
              <Input value={newPhone} onChange={(e) => setNewPhone(e.target.value)} />

              <label className="block text-sm font-medium">Ảnh đại diện (URL)</label>
              <Input value={newImage} onChange={(e) => setNewImage(e.target.value)} />

              <Button onClick={handleSave} disabled={loading || isUnchanged}>
                {loading ? "Đang lưu..." : "Lưu"}
              </Button>
            </div>
          </DialogContent>
        </Dialog>
      </div>

      {newImage && (
        <div className="mb-4">
          <img
            src={newImage}
            alt="avatar"
            className="w-24 h-24 rounded-full object-cover border"
          />
        </div>
      )}

      <div className="prose prose-blue prose-sm md:prose-base max-w-none text-gray-700">
        <ReactMarkdown remarkPlugins={[remarkGfm]}>
          {newBio || "_Không có mô tả nào._"}
        </ReactMarkdown>
      </div>
      <div className="mt-6 text-sm text-gray-600 space-y-1">
        <p>
          <strong>Email:</strong> {email || "Không rõ"}
        </p>
        <p>
          <strong>Địa chỉ:</strong> {newAddress || "Chưa cập nhật"}
        </p>
        <p>
          <strong>Số điện thoại:</strong> {newPhone || "Chưa cập nhật"}
        </p>
      </div>
    </div>
  );
}
