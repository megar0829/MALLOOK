/** @type {import('next').NextConfig} */
const nextConfig = {
    images: {
        remotePatterns: [
            {
                protocol: 'https',
                hostname: 'naver.github.io',
                port: '',
                pathname: '/egjs-infinitegrid/assets/image/**',
            },
        ],
    },
};

export default nextConfig;

