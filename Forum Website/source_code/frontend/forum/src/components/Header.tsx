"use client";

import Link from "next/link";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import AuthButtons from "@/app/(auth)/components/AuthButtons";
import { User } from "../app/post/types";
import SearchBar from "@/components/SearchBar";

interface HeaderProps {
  user: User | null;
  handleLogout: () => void;
}

export default function Header({ user, handleLogout }: HeaderProps) {

  return (
    <header className="fixed top-0 left-0 w-full z-50 bg-white shadow px-6 py-4 flex items-center justify-between">
      {/* Logo và thanh tìm kiếm */}
      <div className="flex items-center gap-6">
        <h1 className="text-xl font-bold text-blue-600">DevShare Lite</h1>

        <SearchBar />
      </div>

      {/* Navigation links */}
      <nav className="flex space-x-8 items-center gap-6">
        <Link
          href="/"
          className="font-semibold text-gray-700 hover:text-blue-600"
        >
          Trang chủ
        </Link>
        <Link
          href="/all-posts"
          className="font-semibold text-gray-700 hover:text-blue-600"
        >
          Diễn đàn
        </Link>
        <Link
          href="/post"
          className="font-semibold text-gray-700 hover:text-blue-600"
        >
          Đăng bài
        </Link>
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button
              variant="ghost"
              className="font-semibold text-gray-700 hover:text-blue-600"
            >
              Chuyên mục
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent>
            <DropdownMenuItem asChild>
              <Link href="/category/1">Lập trình</Link>
            </DropdownMenuItem>
            <DropdownMenuItem asChild>
              <Link href="/category/2">Thiết kế</Link>
            </DropdownMenuItem>
            <DropdownMenuItem asChild>
              <Link href="/category/3">Hỏi đáp</Link>
            </DropdownMenuItem>
            <DropdownMenuItem asChild>
              <Link href="/category/4">Công nghệ</Link>
            </DropdownMenuItem>
            <DropdownMenuItem asChild>
              <Link href="/category/5">Tin tức</Link>
            </DropdownMenuItem>
            <DropdownMenuItem asChild>
              <Link href="/category/6">Khác</Link>
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
        <Link
          href="/members"
          className="font-semibold text-gray-700 hover:text-blue-600"
        >
          Thành viên
        </Link>
      </nav>

      {/* User section */}
      {user ? (
        <div className="flex items-center gap-4">
          <span className="text-sm">
            Xin chào, <strong>{user.user_name}</strong>
          </span>
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button
                size="icon"
                variant="ghost"
                className="rounded-full bg-gray-100"
              >
                <div className="w-8 h-8 rounded-full bg-black text-white flex items-center justify-center text-sm font-semibold">
                  {user.user_name.charAt(0).toUpperCase()}
                </div>
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent className="w-56" align="end">
              <DropdownMenuItem asChild>
                <Link href="/user_profile">Hồ sơ</Link>
              </DropdownMenuItem>
              <DropdownMenuItem className="text-red-600" onClick={handleLogout}>
                Logout
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      ) : (
        <div className="flex items-center gap-2 min-w-[180px] justify-end">
          <AuthButtons />
        </div>
      )}
    </header>
  );
}
