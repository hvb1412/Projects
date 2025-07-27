export default function PostContent({ content }: { content: string }) {
  return (
    <div className="bg-white p-6 rounded-xl shadow text-gray-800 whitespace-pre-line leading-relaxed mb-10">
      {content}
    </div>
  );
}
