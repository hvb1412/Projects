"use client";

import Link from "next/link";
import { Button } from "@/components/ui/button";

interface Props {
  user: { user_name: string } | null;
}

export default function CallToAction({ user }: Props) {
  return (
    <section className="text-center py-16 bg-blue-50 mt-12">
      <h3 className="text-2xl font-semibold mb-4">Bạn sẵn sàng tham gia chưa?</h3>
      <Link href={user ? "/post" : "/login"}>
        <Button className="px-6 text-base">Bắt đầu đăng bài</Button>
      </Link>
    </section>
  );
}
