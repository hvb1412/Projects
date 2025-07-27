"use client";

import Link from "next/link";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

export default function MinimalHeader() {
  return (
    <header className="w-full px-6 py-4 bg-white shadow fixed top-0 left-0 z-10">
      <div className="flex items-center justify-between">
        {/* Logo bên trái */}
        <h1 className="text-xl font-bold text-blue-600">DevShare Lite</h1>

        {/* Navigation center */}
        <nav className="flex space-x-8 items-center">
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

        {/* Placeholder bên phải để giữ nav căn giữa */}
        <div className="w-[120px]" />
      </div>
    </header>
  );
}
