export default async function UseSleep(delay: number) {
		await new Promise<any>(resolve => setTimeout(resolve, delay));
}