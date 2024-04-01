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
            {
                protocol: 'https',
                hostname: 'image.brandi.me',
                port: '',
                pathname: '/common/**',
            },
        ],
    },
};

export default nextConfig;

