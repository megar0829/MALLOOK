export const inrange = (v: number, min: number, max: number) => {
	if (v < min) return min;
	if (v > max) return max;
	return v;
};

export const useSleep = (delay: number) =>
	new Promise<any>(resolve => setTimeout(resolve, delay));