import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from '@/components/ui/button';

const LandingPage = () => {
    return (
        <section className="space-y-6 pb-8 pt-6 md:pb-12 md:pt-10 lg:py-32">
            <div className="container flex max-w-[64rem] flex-col items-center gap-4 text-center m-auto">
                <h1 className="font-heading text-3xl sm:text-5xl md:text-6xl lg:text-7xl">
                    Organize your work with TodoVite.
                </h1>
                <p className="max-w-[42rem] leading-normal text-muted-foreground sm:text-xl sm:leading-8">
                    A simple, elegant, and secure todo list application built with the modern stack.
                    Get more done with less friction.
                </p>
                <div className="space-x-4">
                    <Link to="/register">
                        <Button size="lg">Get Started</Button>
                    </Link>
                    <Link to="/login">
                        <Button variant="outline" size="lg">Login</Button>
                    </Link>
                </div>
            </div>
        </section>
    );
};

export default LandingPage;
