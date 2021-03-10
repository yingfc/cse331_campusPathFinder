import React from 'react';

export interface Point {
    x: number;
    y: number;
}

export interface Segment {
    start: Point;
    end: Point;
    cost: number;
}

export interface Path {
    start: Point;
    path: Segment[];
}