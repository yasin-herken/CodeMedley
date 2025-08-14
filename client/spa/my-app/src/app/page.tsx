'use client';

import {useState} from 'react';

interface Book {
    name: string;
    // Add other book properties as needed
}

export default function Home() {
    const [books, setBooks] = useState<Book[]>([]);

    const loadBooks = async () => {
        try {
            const response = await fetch('http://localhost:8080/books');
            const data: Book[] = await response.json();
            setBooks(data);
        } catch (error) {
            console.error('Error loading books:', error);
        }
    };

    return (
        <div>
            <h1>Book List App</h1>
            <p>
                <button onClick={loadBooks}>Load Books</button>
            </p>
            <span>
        {books.map((book, index) => (
            <div key={index}>{book.name}</div>
        ))}
      </span>
        </div>
    );
}
