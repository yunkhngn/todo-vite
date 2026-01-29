import React, { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Checkbox } from '@/components/ui/checkbox';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { Separator } from '@/components/ui/separator';
import { Trash2 } from 'lucide-react';

const TodoPage = () => {
    const [todos, setTodos] = useState([
        { id: 1, title: 'Buy groceries', completed: false },
        { id: 2, title: 'Walk the dog', completed: true },
        { id: 3, title: 'Finish the project', completed: false },
    ]);
    const [newTodo, setNewTodo] = useState('');

    const toggleTodo = (id) => {
        setTodos(todos.map(todo =>
            todo.id === id ? { ...todo, completed: !todo.completed } : todo
        ));
    };

    const deleteTodo = (id) => {
        setTodos(todos.filter(todo => todo.id !== id));
    };

    const addTodo = (e) => {
        e.preventDefault();
        if (!newTodo.trim()) return;
        setTodos([...todos, { id: Date.now(), title: newTodo, completed: false }]);
        setNewTodo('');
    };

    return (
        <div className="flex justify-center">
            <Card className="w-full max-w-2xl">
                <CardHeader>
                    <CardTitle>My Todos</CardTitle>
                    <CardDescription>Manage your daily tasks efficiently.</CardDescription>
                </CardHeader>
                <CardContent className="space-y-6">
                    <form onSubmit={addTodo} className="flex space-x-2">
                        <Input
                            placeholder="Add a new task..."
                            value={newTodo}
                            onChange={(e) => setNewTodo(e.target.value)}
                        />
                        <Button type="submit">Add</Button>
                    </form>
                    <Separator />
                    <div className="space-y-4">
                        {todos.length === 0 ? (
                            <p className="text-center text-sm text-muted-foreground py-6">
                                No tasks yet. Add one above!
                            </p>
                        ) : (
                            todos.map((todo) => (
                                <div key={todo.id} className="flex items-center justify-between group">
                                    <div className="flex items-center space-x-2">
                                        <Checkbox
                                            id={`todo-${todo.id}`}
                                            checked={todo.completed}
                                            onCheckedChange={() => toggleTodo(todo.id)}
                                        />
                                        <label
                                            htmlFor={`todo-${todo.id}`}
                                            className={`text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70 ${todo.completed ? 'line-through text-muted-foreground' : ''
                                                }`}
                                        >
                                            {todo.title}
                                        </label>
                                    </div>
                                    <Button
                                        variant="ghost"
                                        size="icon"
                                        className="opacity-0 group-hover:opacity-100 transition-opacity text-destructive hover:text-destructive hover:bg-destructive/10"
                                        onClick={() => deleteTodo(todo.id)}
                                    >
                                        <Trash2 className="h-4 w-4" />
                                    </Button>
                                </div>
                            ))
                        )}
                    </div>
                </CardContent>
            </Card>
        </div>
    );
};

export default TodoPage;
