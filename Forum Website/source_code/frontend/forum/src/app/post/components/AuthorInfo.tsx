export default function AuthorInfo({
  categories,
  tags,
}: {
  categories: string[];
  tags: string[];
}) {
  return (
    <div className="flex flex-wrap gap-2 mb-4 text-sm">
      {categories.map((cat) => (
        <span
          key={cat}
          className="bg-blue-100 text-blue-800 px-3 py-1 rounded-full"
        >
          #{cat}
        </span>
      ))}
      {tags.map((tag) => (
        <span
          key={tag}
          className="bg-green-100 text-green-800 px-3 py-1 rounded-full"
        >
          #{tag}
        </span>
      ))}
    </div>
  );
}
