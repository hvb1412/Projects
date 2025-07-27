"use client";

import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Card, CardContent } from "@/components/ui/card";
import { usePostForm } from "../hooks/usePostForm";

export default function PostForm() {
  const {
    title, setTitle,
    content, setContent,
    setCategory,
    tags, setTags,
    setIsDraft,
    availableTags,
    handleSubmit,
  } = usePostForm();

  return (
    <Card className="shadow-lg rounded-2xl bg-white">
      <CardContent className="p-8 space-y-6">
        <form onSubmit={handleSubmit} className="space-y-5">
          <div className="space-y-2">
            <Label htmlFor="title">Tiêu đề bài viết</Label>
            <Input id="title" value={title} onChange={(e) => setTitle(e.target.value)} />
          </div>

          <div className="space-y-2">
            <Label htmlFor="content">Nội dung</Label>
            <Textarea id="content" rows={8} value={content} onChange={(e) => setContent(e.target.value)} />
          </div>

          <div className="space-y-2">
            <Label>Chuyên mục</Label>
            <Select onValueChange={setCategory}>
              <SelectTrigger><SelectValue placeholder="Chọn chuyên mục" /></SelectTrigger>
              <SelectContent>
                {[1, 2, 3, 4, 5, 6].map((id) => (
                  <SelectItem key={id} value={id.toString()}>{["Lập trình", "Thiết kế", "Hỏi đáp", "Công nghệ", "Tin tức", "Khác"][id - 1]}</SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="space-y-2">
            <Label>Thẻ (tags)</Label>
            <div className="flex flex-wrap gap-2">
              {availableTags.map(tag => (
                <Button
                  key={tag.name}
                  type="button"
                  variant={tags.includes(tag.name) ? "default" : "outline"}
                  size="sm"
                  onClick={() =>
                    setTags(prev =>
                      prev.includes(tag.name)
                        ? prev.filter(t => t !== tag.name)
                        : [...prev, tag.name]
                    )
                  }
                >
                  {tag.name}
                </Button>
              ))}
            </div>
          </div>

          <div className="flex justify-center gap-4 pt-2">
            <Button type="submit" variant="outline" onClick={() => setIsDraft(true)}>Lưu nháp</Button>
            <Button type="submit" onClick={() => setIsDraft(false)}>Đăng bài</Button>
          </div>
        </form>
      </CardContent>
    </Card>
  );
}
