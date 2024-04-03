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
                hostname: 'zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com',
                port: '',
                pathname: '/mallook/**',
            },
        ],
    },
};
export default nextConfig;

