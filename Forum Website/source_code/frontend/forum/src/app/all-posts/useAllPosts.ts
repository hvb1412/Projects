"use client";

import { useEffect, useState } from "react";
import { Post } from "./types";

const POSTS_PER_PAGE = 5;

export function useAllPosts() {
  const [posts, setPosts] = useState<Post[]>([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(1);
  const [totalPosts, setTotalPosts] = useState(0);

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8000/category?page=${page}&limit=${POSTS_PER_PAGE}`)
      .then((res) => res.json())
      .then((data) => {
        setPosts(data.data || []);
        setTotalPosts(data.total || 0);
      })
      .catch(() => setPosts([]))
      .finally(() => setLoading(false));
  }, [page]);

  return {
    posts,
    loading,
    page,
    setPage,
    totalPages: Math.ceil(totalPosts / POSTS_PER_PAGE),
  };
}
