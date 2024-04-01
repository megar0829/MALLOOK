"use client";

import { inrange } from '@/utils';
import registDragEvent from '@/utils/registDragEvent';
import { useEffect, useRef, useState } from 'react';
import Boundary from "../_components/Boundary";
import styles from "./worldCupStartPage.module.css";

const BOUNDARY_MARGIN = 12;
const MIN_W = 80;
const MIN_H = 80;
const viewportWidth = window.innerWidth;
const viewportHeight = window.innerHeight;

const pixelsFor10dvw = viewportWidth * 0.1;
const pixelsFor10dvh = viewportHeight * 0.1;

export default function WorldCupStartPage() {
  const boundaryRef = useRef<HTMLDivElement>(null);
  const boxRef = useRef<HTMLDivElement>(null);

  const [{ x, y, w, h }, setConfig] = useState({
    x: 0,
    y: 0,
    w: pixelsFor10dvw,
    h: pixelsFor10dvh
  });
  const [show, setShow] = useState(true);

  useEffect(() => {
    const boundary = boundaryRef.current?.getBoundingClientRect();
    const box = boxRef.current?.getBoundingClientRect();

    if (boundary && box) {
      setConfig({
        x: Math.floor(boundary.width / 2 - box.width / 2),
        y: Math.floor(boundary.height / 2 - box.height / 2),
        w,
        h,
      });
    }
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.leftDiv}>
        <div className="mb-2">
          <h1 className="text-3xl font-bold">Drag Size</h1>
          <div>
            <span>resize the element size with boundary</span>
            <span className="ml-4">
              x:{Math.floor(x)} y:{Math.floor(y)}
            </span>
            <span className="ml-4">
              w:{Math.floor(w)} h:{Math.floor(h)}
            </span>
          </div>
          <div className="flex items-center gap-1">
            <label htmlFor="show">show active playground</label>
            <input id="show" type="checkbox" checked={show} onChange={() => setShow(!show)} />
          </div>
        </div>
        <Boundary ref={boundaryRef}>
          <div
            style={{ width: w, height: h, left: x, top: y }}
            className={styles.boxDiv}
            {...registDragEvent((deltaX, deltaY) => {
              if (!boundaryRef.current) return;

              const boundary = boundaryRef.current.getBoundingClientRect();
              console.log(boundary.top, boundary.bottom)
              console.log(-(boundary.top - boundary.height), boundary.top - boundary.height);
              console.log(y + deltaY)
              setConfig({
                x: inrange(x + deltaX, -250, 250),
                y: inrange(y + deltaY, -300, 300),
                w,
                h,
              });
            })}
          >
            <Box />

            {/* 좌상단 */}
            <div
              className={styles.box__leftTop}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((deltaX, deltaY) => {
                setConfig({
                  x: inrange(x + deltaX, BOUNDARY_MARGIN, x + w - MIN_W),
                  y: inrange(y + deltaY, BOUNDARY_MARGIN, y + h - MIN_H),
                  w: inrange(w - deltaX, MIN_W, x + w - BOUNDARY_MARGIN),
                  h: inrange(h - deltaY, MIN_H, y + h - BOUNDARY_MARGIN),
                });
              }, true)}
            />
            {/* 우상단 */}
            <div
              className={styles.box__rightTop}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((deltaX, deltaY) => {
                if (!boundaryRef.current) return;

                const boundary = boundaryRef.current.getBoundingClientRect();

                setConfig({
                  x,
                  y: inrange(y + deltaY, BOUNDARY_MARGIN, y + h - MIN_H),
                  w: inrange(w + deltaX, MIN_W, boundary.width - x - BOUNDARY_MARGIN),
                  h: inrange(h - deltaY, MIN_H, y + h - BOUNDARY_MARGIN),
                });
              }, true)}
            />
            {/* 좌하단 */}
            <div
              className={styles.box__leftBottom}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((deltaX, deltaY) => {
                if (!boundaryRef.current) return;

                const boundary = boundaryRef.current.getBoundingClientRect();

                setConfig({
                  x: inrange(x + deltaX, BOUNDARY_MARGIN, x + w - MIN_W),
                  y,
                  w: inrange(w - deltaX, MIN_W, x + w - BOUNDARY_MARGIN),
                  h: inrange(h + deltaY, MIN_H, boundary.height - y - BOUNDARY_MARGIN),
                });
              }, true)}
            />
            {/* 우하단 */}
            <div
              className={styles.box__rightBottom}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((deltaX, deltaY) => {
                if (!boundaryRef.current) return;

                const boundary = boundaryRef.current.getBoundingClientRect();

                setConfig({
                  x,
                  y,
                  w: inrange(w + deltaX, MIN_W, boundary.width - x - BOUNDARY_MARGIN),
                  h: inrange(h + deltaY, MIN_H, boundary.height - y - BOUNDARY_MARGIN),
                });
              }, true)}
            />
            {/* 상단 */}
            <div
              className={styles.box__top}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((_, deltaY) => {
                setConfig({
                  x,
                  y: inrange(y + deltaY, BOUNDARY_MARGIN, y + h - MIN_H),
                  w,
                  h: inrange(h - deltaY, MIN_H, y + h - BOUNDARY_MARGIN),
                });
              }, true)}
            />
            {/* 하단 */}
            <div
              className={styles.box__bottom}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((_, deltaY) => {
                if (!boundaryRef.current) return;

                const boundary = boundaryRef.current.getBoundingClientRect();

                setConfig({
                  x,
                  y,
                  w,
                  h: inrange(h + deltaY, MIN_H, boundary.height - y - BOUNDARY_MARGIN),
                });
              }, true)}
            />
            {/* 우측 */}
            <div
              className={styles.box__right}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((deltaX) => {
                if (!boundaryRef.current) return;

                const boundary = boundaryRef.current.getBoundingClientRect();

                setConfig({
                  x,
                  y,
                  w: inrange(w + deltaX, MIN_W, boundary.width - x - BOUNDARY_MARGIN),
                  h,
                });
              }, true)}
            />
            {/* 좌측 */}
            <div
              className={styles.box__left}
              style={{ backgroundColor: show ? '#12121250' : 'transparent' }}
              {...registDragEvent((deltaX) => {
                setConfig({
                  x: inrange(x + deltaX, BOUNDARY_MARGIN, x + w - MIN_W),
                  y,
                  w: inrange(w - deltaX, MIN_W, x + w - BOUNDARY_MARGIN),
                  h,
                });
              }, true)}
            />
          </div>
        </Boundary>
      </div>

      <div className={styles.rightDiv}>

      </div>
    </div>
  );
}

const Box = () => (
  <div className={styles.box} />
);
