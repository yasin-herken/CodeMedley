'use client';

import {useState} from 'react';

interface Book {
    id: string;
    name: string;
}

export default function BookListApp() {
    const [books, setBooks] = useState<Book[]>([]);
    const [username, setUsername] = useState('');

    const loadBooks = async () => {
        try {
            const response = await fetch('http://localhost:8080/books', {
                headers: {
                    //'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error('Failed to load books');
            }

            const data: Book[] = await response.json();
            setBooks(data);
        } catch (error) {
            console.error('Error loading books:', error);
        }
    };

    return (
        <div>
            <h1>Book List App</h1>
            (<span>
          {books.map((book) => (
              <div key={book.id}>
                  {book.name}
              </div>
          ))}
        </span>
            )
        </div>
    );
}