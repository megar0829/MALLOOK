"use client";


import Link from "next/link";
import {useRef, useState} from "react";
import styles from "./worldCupStartPage.module.css";

const inrange = (v: number, min: number, max: number) => {
  if (v < min) return min;
  if (v > max) return max;
  return v;
};

export function registMouseDownDrag(
  onDragChange: (deltaX: number, deltaY: number) => void,
  stopPropagation?: boolean,
) {
  return {
    onMouseDown: (clickEvent: React.MouseEvent<Element, MouseEvent>) => {
      if (stopPropagation) clickEvent.stopPropagation();

      const mouseMoveHandler = (moveEvent: MouseEvent) => {
        const deltaX = moveEvent.screenX - clickEvent.screenX;
        const deltaY = moveEvent.screenY - clickEvent.screenY;
        onDragChange(deltaX, deltaY);
      };

      const mouseUpHandler = () => {
        document.removeEventListener('mousemove', mouseMoveHandler);
      };

      document.addEventListener('mousemove', mouseMoveHandler);
      document.addEventListener('mouseup', mouseUpHandler, { once: true });
    },
  };
}

export default function WorldCupStartPage() {
  const boundaryRef = useRef<HTMLDivElement>(null);
  const boxRef = useRef<HTMLDivElement>(null);

  const [{x, y}, setPosition] = useState({
    x: 0,
    y: 0,
  });

  return (
    <div className={styles.container}>
      <div className={styles.leftDiv} ref={boundaryRef}>

        <div
          ref={boxRef}
          className={styles.box}
          style={{ left: x, top: y }}
          onMouseDown={(e) => {
            const initX = e.pageX;
            const initY = e.pageY;

            const mouseMoveHandler = (e: MouseEvent) => {
              if (boundaryRef.current && boxRef.current) {
                const boundary = boundaryRef.current.getBoundingClientRect();
                const box = boxRef.current.getBoundingClientRect();
                const BOUNDARY_MARGIN = 12;

                const deltaX = e.pageX - initX;
                const deltaY = e.pageY - initY;

                setPosition({
                  x: inrange(
                    x + deltaX,
                    Math.floor(-boundary.width / 2 + box.width / 2 + BOUNDARY_MARGIN),
                    Math.floor(boundary.width / 2 - box.width / 2 - BOUNDARY_MARGIN),
                  ),
                  y: inrange(
                    y + deltaY,
                    Math.floor(-boundary.height / 2 + box.height / 2 + BOUNDARY_MARGIN),
                    Math.floor(boundary.height / 2 - box.height / 2 - BOUNDARY_MARGIN),
                  ),
                });
              }
            };
            const mouseUpHandler = (e: MouseEvent) => {
              document.removeEventListener('mousemove', mouseMoveHandler);
            };

            document.addEventListener('mousemove', mouseMoveHandler);
            document.addEventListener('mouseup', mouseUpHandler, { once: true });
          }}
        />
      </div>

      <div className={styles.rightDiv}>

      </div>
    </div>
  );
}
