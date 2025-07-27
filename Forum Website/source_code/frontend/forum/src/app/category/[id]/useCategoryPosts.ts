"use client";

import { useEffect, useState } from "react";

interface Post {
  post_id: number;
  title: string;
  content: string;
  created_at: string;
  author_name: string;
  tags: string[];
}

const POSTS_PER_PAGE = 5;

export const useCategoryPosts = (id: string | undefined) => {
  const [posts, setPosts] = useState<Post[]>([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(1);
  const [totalPosts, setTotalPosts] = useState(0);

  useEffect(() => {
    if (!id) return;
    setLoading(true);
    fetch(`http://localhost:8000/category/${id}?page=${page}&limit=${POSTS_PER_PAGE}`)
      .then((res) => res.json())
      .then((data) => {
        setPosts(data.data || []);
        setTotalPosts(data.total || 0);
      })
      .catch(() => setPosts([]))
      .finally(() => setLoading(false));
  }, [id, page]);

  const totalPages = Math.ceil(totalPosts / POSTS_PER_PAGE);

  return { posts, loading, page, setPage, totalPages };
};
