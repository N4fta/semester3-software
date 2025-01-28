import BASWorldLogoNoText from "@/assets/bas_world_logo.webp";
import "@/App.css";

const LoadingOverlay = () => {
  return (
    <div className="w-full h-full flex items-center justify-center bg-gray-100 absolute inset-0 z-[9999]">
      <div className="mt-[-100px]">
        <img
          className="w-[10rem] aspect-square"
          style={{
            animation: "pulseAndSpin 15s infinite linear", // Adjust the speed here
          }}
          src={BASWorldLogoNoText}
          alt="Loading"
        />
      </div>
    </div>
  );
};
export default LoadingOverlay;
