"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Search } from "lucide-react";

export default function SearchBar() {
  const [expanded, setExpanded] = useState(false);
  const [focused, setFocused] = useState(false);
  const [term, setTerm] = useState("");
  const router = useRouter();

  const handleSearch = () => {
    if (term.trim()) {
      router.push(`/search?query=${encodeURIComponent(term.trim())}`);
    }
  };

  return (
    <div
      className="flex items-center transition-all duration-300"
      onMouseEnter={() => setExpanded(true)}
      onMouseLeave={() => {
        if (!focused && !term) setExpanded(false);
      }}
    >
      <Button
        variant="ghost"
        size="icon"
        className="p-3 text-gray-900 hover:text-blue-600 bg-gray-100 hover:bg-gray-200 rounded-full font-semibold"
        onClick={() => setExpanded(true)}
      >
        <Search className="w-6 h-6" />
      </Button>

      {/* Thanh input và nút Tìm được hiển thị khi mở rộng */}
      {expanded && (
        <>
          <Input
            type="text"
            placeholder="Tìm kiếm..."
            value={term}
            onChange={(e) => setTerm(e.target.value)}
            onFocus={() => setFocused(true)}
            onBlur={() => {
              setFocused(false);
              if (!term) setExpanded(false);
            }}
            onKeyDown={(e) => e.key === "Enter" && handleSearch()}
            className="w-64 ml-2 transition-all duration-300"
          />
          <Button
            onClick={handleSearch}
            className="ml-2 text-sm bg-blue-600 text-white hover:bg-blue-700 transition-all duration-300"
          >
            Tìm
          </Button>
        </>
      )}
    </div>
  );
}
