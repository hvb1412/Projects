"use client";

import { ArrowLeft, FileText, LogOut, User } from "lucide-react";
import { useRouter } from "next/navigation";
import { AppRouterInstance } from "next/dist/shared/lib/app-router-context.shared-runtime";

interface SidebarProps {
  activeTab: "about" | "posts";
  setActiveTab: React.Dispatch<React.SetStateAction<"about" | "posts">>;
  userName: string;
  handleLogout: () => void;
  router: AppRouterInstance;
}


export default function Sidebar({ activeTab, setActiveTab, userName }: SidebarProps) {
  const router = useRouter();

  const handleLogout = () => {
    localStorage.removeItem("user");
    router.push("/login");
  };

  return (
    <aside className="w-64 bg-white border-r border-gray-200 p-4 shadow-sm flex flex-col justify-between fixed top-0 left-0 h-screen overflow-y-auto">
      <div>
        <div className="flex items-center gap-3 mb-6">
          <div className="w-12 h-12 rounded-full bg-blue-600 flex items-center justify-center text-white font-bold text-lg">
            {userName.charAt(0).toUpperCase()}
          </div>
          <div className="font-semibold text-gray-800 text-lg">{userName}</div>
        </div>

        <button
          onClick={() => setActiveTab("about")}
          className={`flex items-center gap-2 px-3 py-2 rounded-md text-sm font-semibold w-full ${
            activeTab === "about"
              ? "bg-blue-100 text-blue-700"
              : "hover:bg-gray-100"
          }`}
        >
          <User size={18} /> About
        </button>

        <button
          onClick={() => setActiveTab("posts")}
          className={`flex items-center gap-2 px-3 py-2 rounded-md text-sm font-semibold w-full ${
            activeTab === "posts"
              ? "bg-blue-100 text-blue-700"
              : "hover:bg-gray-100"
          }`}
        >
          <FileText size={18} /> Posts
        </button>
      </div>

      <div className="flex flex-col gap-2">
        <button
          onClick={handleLogout}
          className="flex items-center gap-2 text-red-600 px-3 py-2 rounded-md text-sm font-semibold hover:bg-red-50"
        >
          <LogOut size={18} /> Logout
        </button>
        <button
          onClick={() => router.push("/")}
          className="flex items-center gap-2 text-gray-600 px-3 py-2 rounded-md text-sm font-semibold hover:bg-gray-100"
        >
          <ArrowLeft size={18} /> Back
        </button>
      </div>
    </aside>
  );
}
