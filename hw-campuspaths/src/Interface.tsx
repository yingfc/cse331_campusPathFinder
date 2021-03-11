import React from 'react';

export interface Point {
    x: number;  // the x-coordinate of a point
    y: number;  // the y=coordinate of a point
}

export interface Segment {
    start: Point;   // the start point of a segment
    end: Point;     // the end point of a segment
    cost: number;   // the cost of a segment
}

export interface Path {
    start: Point;       // the start point of a path
    path: Segment[];    // the actual path of list of segments
}