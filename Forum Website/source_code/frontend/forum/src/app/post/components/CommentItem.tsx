import { useState } from "react";
import { Button } from "@/components/ui/button";

interface Comment {
  comment_id: number;
  user_name: string;
  content: string;
  parent_id: number | null;
  created_at: string;
  replies?: Comment[];
}

export default function CommentItem({
  comment,
  currentUserName,
  onReplySubmit,
}: {
  comment: Comment;
  currentUserName?: string;
  onReplySubmit: (parentId: number, content: string) => void;
}) {
  const [showReply, setShowReply] = useState(false);
  const [replyContent, setReplyContent] = useState("");

  const handleReply = () => {
    if (!replyContent.trim()) return;
    onReplySubmit(comment.comment_id, replyContent);
    setReplyContent("");
    setShowReply(false);
  };

  return (
    <div className="pl-2">
      <div className="border-b border-gray-200 pb-2">
        <div className="flex items-center gap-2 mb-1">
          <div className="w-8 h-8 rounded-full bg-blue-600 text-white flex items-center justify-center text-sm font-bold">
            {comment.user_name.charAt(0).toUpperCase()}
          </div>
          <p className="text-sm font-semibold">
            {comment.user_name}
            {currentUserName === comment.user_name && (
              <span className="text-xs text-blue-600 ml-1">(me)</span>
            )}
          </p>
        </div>
        <p className="text-gray-800 text-sm mb-1 whitespace-pre-line">{comment.content}</p>
        <p className="text-xs text-gray-400">
          {new Date(comment.created_at).toLocaleDateString("vi-VN")} {" "}
          {new Date(comment.created_at).toLocaleTimeString("vi-VN")}
        </p>

        <button
          className="text-sm text-blue-500 mt-1 cursor-pointer"
          onClick={() => setShowReply(!showReply)}
        >
          {showReply ? "Hủy" : "Trả lời"}
        </button>

        {showReply && (
          <div className="mt-2 pl-4">
            <textarea
              className="w-full p-2 border rounded"
              value={replyContent}
              onChange={(e) => setReplyContent(e.target.value)}
              placeholder="Viết câu trả lời..."
            />
            <Button size="sm" onClick={handleReply} className="mt-1">
              Gửi
            </Button>
          </div>
        )}
      </div>

      {comment.replies && comment.replies.length > 0 && (
        <div className="mt-2 pl-4 border-l border-gray-300">
          {comment.replies.map((reply) => (
            <CommentItem
              key={reply.comment_id}
              comment={reply}
              currentUserName={currentUserName}
              onReplySubmit={onReplySubmit}
            />
          ))}
        </div>
      )}
    </div>
  );
}