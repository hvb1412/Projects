"use client";

import Link from "next/link";
import { Card, CardContent } from "@/components/ui/card";

const categories = [
  { id: 1, name: "Lập trình" },
  { id: 2, name: "Thiết kế" },
  { id: 3, name: "Hỏi đáp" },
  { id: 4, name: "Công nghệ" },
  { id: 5, name: "Tin tức" },
  { id: 6, name: "Khác" },
];

export default function CategoryGrid() {
  return (
    <section className="py-12 px-6 max-w-5xl mx-auto">
      <h3 className="text-2xl font-semibold mb-6">Chuyên mục</h3>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {categories.map((cat) => (
          <Link href={`/category/${cat.id}`} key={cat.id}>
            <Card className="hover:shadow-lg cursor-pointer transition">
              <CardContent className="p-6">
                <h4 className="text-lg font-medium mb-1">{cat.name}</h4>
                <p className="text-sm text-gray-500">
                  Xem các chủ đề và bài viết trong chuyên mục này.
                </p>
              </CardContent>
            </Card>
          </Link>
        ))}
      </div>
    </section>
  );
}
