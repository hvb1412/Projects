"use client";

import { LoginForm } from "@/app/(auth)/login/login-form";
import "./loginpage.scss";
import Link from "next/link";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Button } from "@/components/ui/button";
import MinimalHeader from "@/app/(auth)/components/MinimalHeader";

const categories = [
  { id: 1, name: "Lập trình" },
  { id: 2, name: "Thiết kế" },
  { id: 3, name: "Hỏi đáp" },
  { id: 4, name: "Công nghệ" },
  { id: 5, name: "Tin tức" },
  { id: 6, name: "Khác" },
];

const LoginPage = () => {
  return (
    <div className="login-background">
      {/* Thanh header ở trên đầu */}
      <MinimalHeader />

      {/* Container chính có margin-top để tránh đè lên header */}
      <div className="login-container mt-20">
        <div className="login-title">Login</div>
        <LoginForm />
        <div className="or-divider">Or</div>

        <div className="login-link">
          Don&apos;t have an account yet?
          <Link href="/register" className="text-blue-600 hover:underline ml-1">
            Sign up
          </Link>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
